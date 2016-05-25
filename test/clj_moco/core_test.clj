(ns clj-moco.core-test
  (:require [clojure.test :refer :all]
            [clj-moco.core :refer :all]
            [clj-moco.extractor-matcher :refer :all]
            [clj-http.client :as client]))

(defn- run
  [server f]
  (try 
    (.start server)
    (f)
    (finally
      (.stop server))))

(defn- post-root
  [body]
  (client/post "http://localhost:12306"
               {:headers {"Content-Type" "text/plain"}
                :body body}))

(deftest should-return-content-based-on-jsonpath
  (let [server (-> (http-server 12306)
                   (matches (eq (json-path "$.book.price") "1"))
                   (respond "foo"))]
    (run server #(is (=  "foo" (:body (post-root "{\"book\":{\"price\":\"1\"}}")))))))

(deftest should-throw-exception-when-there-is-no-jsonpath-matched
  (let [server (-> (http-server 12306)
                   (matches (eq (json-path "$.book.price") "1"))
                   (respond "foo"))]
    (run server #(is (thrown? RuntimeException (post-root "{\"book\":{\"price\":\"2\"}}"))))))

(deftest should-match-exact-json
  (let [server (-> (http-server 12306)
                   (matches (json "{\"foo\":\"bar\"}"))
                   (respond "foo"))]
    (run server #(is (= "foo" (:body (post-root "{\"foo\":\"bar\"}")))))))

(deftest should-match-map-json
  (let [server (-> (http-server 12306)
                   (matches (map->json {:code 1 :message "message"}))
                   (respond "foo"))]
    (run server #(is (= "foo" (:body (post-root "{\"code\":1, \"message\":\"message\"}")))))))

(deftest should-match-when-jsonpath-test-exsited
  (let [server (-> (http-server 12306)
                   (matches (exists (json-path "$.book.price")))
                   (respond "foo"))]
    (run server #(is (= "foo" (:body (post-root "{\"book\":{\"price\":\"1\"}}")))))))

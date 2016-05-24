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
  (let [server (-> 12306
                   http-server
                   (matches (eq (json-path "$.book.price") "1"))
                   (respond "foo"))]
    (run server #(is (=  "foo" (:body (post-root "{\"book\":{\"price\":\"1\"}}")))))))

(deftest should-throw-exception-when-there-is-no-jsonpath-matched
  (let [server (-> 12306
                   http-server
                   (matches (eq (json-path "$.book.price") "1"))
                   (respond "foo"))]
    (run server #(is (thrown? RuntimeException (post-root "{\"book\":{\"price\":\"2\"}}"))))))

(deftest should-match-exact-json
  (let [server (-> 12306
                   http-server
                   (matches (json "{\"foo\":\"bar\"}"))
                   (respond "foo"))]
    (run server #(is (= "foo" (:body (post-root "{\"foo\":\"bar\"}")))))))

(deftest should-match-map-json
  (let [server (-> 12306
                   http-server
                   (matches (map->json {:code 1 :message "message"}))
                   (respond "foo"))]
    (run server #(is (= "foo" (:body (post-root "{\"code\":1, \"message\":\"message\"}")))))))

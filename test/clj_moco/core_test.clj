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
  (let [server (respond (matches (http-server 12306) (eq (json-path "$.book.price") "1")) "World")]
    (run server #(is (=  "World" (:body (post-root "{\"book\":{\"price\":\"1\"}}")))))))

(deftest should-throw-exception-when-there-is-no-jsonpath-matched
  (let [server (respond (matches (http-server 12306) (eq (json-path "$.book.price") "1")) "World")]
    (run server #(is (thrown? RuntimeException (post-root "{\"book\":{\"price\":\"2\"}}"))))))


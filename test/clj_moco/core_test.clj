(ns clj-moco.core-test
  (:refer-clojure :exclude [get])
  (:import java.nio.charset.Charset)
  (:require [clj-moco
             [core :refer :all]
             [helper :refer [get root]]
             [resource-handler :refer [->handler ->resource ->file]]
             [runner :refer [run]]]
            [clojure.test :refer :all]))

(deftest should-return-expected-reponse
  (let [server (-> (http-server 12306)
                   (respond "foo"))]
    (run server #(is (= "foo" (:body (get (root))))))))

(deftest should-return-expected-reponse-with-resource
  (let [server (-> (http-server 12306)
                   (respond (->resource "foo")))]
    (run server #(is (= "foo" (:body (get (root))))))))

(deftest should-return-expected-reponse-with-handler
  (let [server (-> (http-server 12306)
                   (respond (->handler "foo")))]
    (run server #(is (= "foo" (:body (get (root))))))))

(deftest should-return-expected-reponse-from-file
  (let [server (-> (http-server 12306)
                   (respond (->file "resources/foo.response")))]
    (run server #(is (= "foo.response" (:body (get (root))))))))

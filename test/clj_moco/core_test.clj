(ns clj-moco.core-test
  (:refer-clojure :exclude [get])
  (:require [clj-moco
             [core :refer :all]
             [extractor-matcher :refer [by uri]]
             [helper :refer [context get root]]
             [resource-handler :refer [->file ->handler ->resource]]
             [runner :refer [run]]]
            [clojure.test :refer :all]))

(deftest should-return-expected-response
  (let [server (-> (http-server 12306)
                   (respond "foo"))]
    (run server #(is (= "foo" (:body (get (root))))))))

(deftest should-return-expected-response-with-resource
  (let [server (-> (http-server 12306)
                   (respond (->resource "foo")))]
    (run server #(is (= "foo" (:body (get (root))))))))

(deftest should-return-expected-response-with-handler
  (let [server (-> (http-server 12306)
                   (respond (->handler "foo")))]
    (run server #(is (= "foo" (:body (get (root))))))))

(deftest should-return-expected-response-from-file
  (let [server (-> (http-server 12306)
                   (respond (->file "resources/foo.response")))]
    (run server #(is (= "foo.response" (:body (get (root))))))))

(deftest should-return-expected-response-based-on-specified-uri
  (let [server (-> (http-server 12306)
                   (matches (by (uri "/foo")))
                   (respond "bar"))]
    (run server #(is (= "bar" (:body (get (context "/foo"))))))))

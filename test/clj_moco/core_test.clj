(ns clj-moco.core-test
  (:refer-clojure :exclude [get])
  (:require [clj-moco
             [core :refer :all]
             [predicates :as p]
             [extractor-matcher :refer [by uri]]
             [helper :refer [context get post-content root]]
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

(deftest should-match-request-on-multiple-matchers
  (let [server (-> (http-server 12306)
                   (matches (p/and (by "foo") (by (uri "/foo"))))
                   (respond "bar"))]
    (run server #(is (= "bar" (:body (post-content "/foo" "foo")))))))

(deftest should-throw-exception-request-when-and-predicates-not-matched
  (let [server (-> (http-server 12306)
                   (matches (p/and (by "foo") (by (uri "/foo"))))
                   (respond "bar"))]
    (run server #(is (thrown? RuntimeException (post-content "/foo" "bar"))))))

(deftest should-match-request-on-any-matchers
  (let [server (-> (http-server 12306)
                   (matches (p/or (by "foo") (by (uri "/foo"))))
                   (respond "bar"))]
    (run server #(is (= "bar" (:body (post-content "/bar" "foo")))))))

(deftest should-throw-exception-when-none-of-predicates-matched
  (let [server (-> (http-server 12306)
                   (matches (p/or (by "foo") (by (uri "/foo"))))
                   (respond "bar"))]
    (run server #(is (thrown? RuntimeException (post-content "/bar" "bar"))))))


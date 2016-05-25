(ns clj-moco.core-test
  (:require [clj-moco
             [core :refer :all]
             [extractor-matcher :refer :all]
             [helper :refer [post-root get root]]
             [runner :refer [run]]]
            [clojure.test :refer :all]))

(deftest should-return-expected-reponse
  (let [server (-> (http-server 12306)
                   (respond "foo"))]
    (run server #(is (= "foo" (:body (get (root))))))))

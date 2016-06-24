(ns clj-moco.helper
  (:refer-clojure :exclude [get])
  (:require [clj-http.client :as client]))

(defn root [& [port]]
  (str "http://localhost:" (or port 12306)))

(defn context [uri]
  (str (root) uri))

(defn post-root [body]
  (client/post (root)
               {:headers {"Content-Type" "text/plain"}
                :body body}))

(defn post-content [uri body]
  (client/post (context uri)
               {:headers {"Content-Type" "text/plain"}
                :body body}))

(defn get [root-url]
  (client/get root-url))

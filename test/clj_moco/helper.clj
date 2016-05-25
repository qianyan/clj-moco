(ns clj-moco.helper
  (:require [clj-http.client :as client]))

(defn root [& [port]]
  (str "http://localhost:" (or port 12306)))

(defn post-root [body]
  (client/post "http://localhost:12306"
               {:headers {"Content-Type" "text/plain"}
                :body body}))

(defn get [root-url]
  (client/get root-url))

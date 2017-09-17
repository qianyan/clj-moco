(defproject clj-moco "0.1.0-SNAPSHOT"
  :description "An easy setup stub framework in Clojure, wrapping Moco"
  :url "https://github.com/qianyan/clj-moco"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :deploy-repositories [["releases" :clojars
                         :creds :gpg]
                        ["snapshots" :clojars
                         :creds :gpg]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.github.dreamhead/moco-core "0.10.2"]
                 [clj-http "3.1.0"]
                 [cheshire "5.6.1"]
                 [org.clojure/test.check "0.9.0"]
                 [prismatic/schema "1.1.1"]
                 [com.stuartsierra/component "0.3.1"]])



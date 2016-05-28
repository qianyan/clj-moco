(ns clj-moco.core
  (:require [schema.core :as s]
            [schema.experimental.generators :as sg])
  (:import [com.github.dreamhead.moco Moco MocoConfig]
           com.github.dreamhead.moco.internal.MocoHttpServer))

(defn http-server [port & _]
  {:server (Moco/httpServer port (into-array MocoConfig _))})

(defn matches [{:keys [server]} matcher]
  {:response-setting (.request server matcher)
   :server server})

(defn respond [{:keys [response-setting server]} content]
  (.response (or response-setting server) content)
  (MocoHttpServer. server))

(s/defschema Customer
  {:name s/Str
   :age s/Int
   :gender s/Str})

(sg/generate Customer)

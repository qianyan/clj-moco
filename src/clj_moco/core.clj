(ns clj-moco.core
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


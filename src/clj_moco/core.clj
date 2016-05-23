(ns clj-moco.core
  (:require [clj-moco.extractor-matcher :refer [eq json-path]]
            [clj-http.client :as client])
  (:import
   (java.util.concurrent TimeUnit)
   (com.github.dreamhead.moco Moco MocoConfig)
   (com.github.dreamhead.moco.config MocoContextConfig MocoFileRootConfig)
   (com.github.dreamhead.moco.internal ActualHttpServer MocoHttpServer)))

(defn ->resource [text]
  (Moco/text text))

(defn ->matcher [resource]
  (Moco/match resource))

(defn ->handler [resource]
  (Moco/with resource))

(defn file-root [path]
  (MocoFileRootConfig. path))

(defn context [context]
  (MocoContextConfig. context))

(defn uri [value]
  (Moco/uri value))

(defn method [value]
  (Moco/method value))

(defn file [filename]
  (Moco/file filename))

(defn version [value]
  (Moco/version value))

(defn xml [content]
  (Moco/xml content))

(defn json [content]
  (Moco/json content))

(defn latency [duration]
  (let [millis :toMillis]
    (Moco/latency (millis duration) TimeUnit/MILLISECONDS)))

(defn status [code]
  (Moco/status code))

(defn attchment [filename resource]
  (Moco/attachment filename resource))

(defn http-server [port & _]
  (Moco/httpServer port (into-array MocoConfig _)))

(defn matches [server matcher]
  {:response-setting (.request server matcher)
   :server server})

(defn respond [{:keys [response-setting server]} content]
  (.response response-setting content)
  (MocoHttpServer. server))


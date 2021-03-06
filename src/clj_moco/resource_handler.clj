(ns clj-moco.resource-handler
  (:require [cheshire.core :refer :all]
            [schema.experimental.generators :as sg])
  (:import com.github.dreamhead.moco.Moco
           java.nio.charset.Charset))

(defn ->resource [text]
  (Moco/text text))

(defn ->handler [text]
  (Moco/with text))

(defn ->file [filename & [charset]]
  (if charset
    (Moco/file ^String filename ^Charset charset)
    (Moco/file filename)))

(defn ->schema [schema]
  (generate-string (sg/generate schema)))

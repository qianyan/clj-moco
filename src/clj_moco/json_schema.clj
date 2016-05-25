(ns clj-moco.json-schema
  (:require [schema.core :as s]
            [schema.experimental.generators :as sg]))

(defschema Customer
  {:name s/Str
   :age s/Int
   :gender s/Str})

(sg/generate Customer)

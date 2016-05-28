(ns clj-moco.extractor-matcher
  (:require [cheshire.core :refer :all])
  (:import
   (com.github.dreamhead.moco Moco)))

(defn eq [extractor expected]
  (Moco/eq extractor expected))

(defn match [extractor expected]
  (Moco/match extractor expected))

(defn contain [extractor expected]
  (Moco/contain extractor expected))

(defn startsWith [extractor expected]
  (Moco/startsWith extractor expected))

(defn exists [extractor]
  (Moco/exist extractor))

(defn endsWith [extractor expected]
  (Moco/endsWith extractor expected))

(defn json-path [jsonpath]
  (Moco/jsonPath jsonpath))

(defn json [json-text]
  (Moco/json ^String json-text))

(defn map->json [map]
  (json (generate-string map)))

(defn by [resource]
  (Moco/by resource))

(defn uri [uri]
  (Moco/uri uri))

(ns clj-moco.extractor-matcher
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

(defn endsWith [extractor expected]
  (Moco/endsWith extractor expected))

(defn json-path [jp]
  (Moco/jsonPath jp))


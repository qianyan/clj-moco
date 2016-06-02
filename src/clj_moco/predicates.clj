(ns clj-moco.predicates
  (:refer-clojure :exclude [or and])
  (:import [com.github.dreamhead.moco Moco RequestMatcher]))

(defn and [& request-matchers]
  (Moco/and  (into-array RequestMatcher request-matchers)))

(defn or [& request-matchers]
  (Moco/or  (into-array RequestMatcher request-matchers)))

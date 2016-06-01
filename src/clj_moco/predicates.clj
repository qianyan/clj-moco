(ns clj-moco.predicates
  (:import [com.github.dreamhead.moco Moco RequestMatcher]))

(defn and [& request-matchers]
  (Moco/and  (into-array RequestMatcher request-matchers)))

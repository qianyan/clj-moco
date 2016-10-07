(ns clj-moco.runner)

(defmacro run
  [server assert-expr]
  `(try
    (.start ~server)
    ~assert-expr
    (finally
      (.stop ~server))))

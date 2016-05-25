(ns clj-moco.runner)

(defn run
  [server f]
  (try
    (.start server)
    (f)
    (finally
      (.stop server))))

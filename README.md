# clj-moco

An easy setup stub framework in Clojure. Wrapping Moco.

## Usage

1\. jsonpath
```clojure
(respond (matches (http-server 12306) (eq (json-path "$.book.price") "1")) "foo")
or
(-> 12306
    http-server
    (matches (eq (json-path "$.book.price") "1"))
    (respond "foo"))
```
2\. json
```clojure
(respond (matches (http-server 12306) (json "{\"foo\":\"bar\"}")) "foo")
or
(-> 12306
    http-server
    (matches (json "{\"foo\":\"bar\"}"))
    (respond "foo"))
```
3\. map in clojure
```clojure
(respond (matches (http-server 12306) (json {:code 1 :message "message"})) "foo")
or
(-> 12306
    http-server
    (matches (map->json {:code 1 :message "message"}))
    (respond "foo"))
```

## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

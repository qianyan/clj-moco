# clj-moco

An easy setup stub framework in Clojure. Wrapping Moco.

## Usage

1\. matches jsonpath
```clojure
(respond (matches (http-server 12306) (eq (json-path "$.book.price") "1")) "foo")
or
(-> (http-server 12306)
    (matches (eq (json-path "$.book.price") "1"))
    (respond "foo"))
```
2\. matches json
```clojure
(respond (matches (http-server 12306) (json "{\"foo\":\"bar\"}")) "foo")
or
(-> (http-server 12306)
    (matches (json "{\"foo\":\"bar\"}"))
    (respond "foo"))
```
3\. matches map jsonpath
```clojure
(respond (matches (http-server 12306) (map->json {:code 1 :message "message"})) "foo")
or
(-> (http-server 12306)
    (matches (map->json {:code 1 :message "message"}))
    (respond "foo"))
```
4\. exists json
```clojure
(respond (matches (http-server 12306) (exists (json-path "$.book.price"))) "foo")
or
(-> (http-server 12306)
    (matches (exists (json-path "$.book.price")))
    (respond "foo"))
```

## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

# clj-moco

An easy setup stub framework in Clojure, wrapping [Moco](https://github.com/dreamhead/moco).

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
3\. matches map
```clojure
(respond (matches (http-server 12306) (map->json {:code 1 :message "message"})) "foo")
or
(-> (http-server 12306)
    (matches (map->json {:code 1 :message "message"}))
    (respond "foo"))
```
4\. exists jsonpath
```clojure
(respond (matches (http-server 12306) (exists (json-path "$.book.price"))) "foo")
or
(-> (http-server 12306)
    (matches (exists (json-path "$.book.price")))
    (respond "foo"))
```
5\. respond something
```clojure
(-> (http-server 12306)
    (respond "foo"))
```

6\. respond a file
```clojure
(-> (http-server 12306)
    (respond (->file "resources/foo.response")))
```

7\. by uri
```clojure
(-> (http-server 12306)
    (matches (by (uri "/foo")))
    (respond "bar"))
```

## License

Copyright © 2016 lambeta

Distributed under the Eclipse Public License either version 1.0

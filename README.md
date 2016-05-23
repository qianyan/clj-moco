# clj-moco

An easy setup stub framework in Clojure. Wrapping Moco.

## Usage
1. jsonpath
```clojure
(respond (matches (http-server 12306) (eq (json-path "$.book.price") "1")) "World")
```
2. json
```clojure
(respond (matches (http-server 12306) (json "{\"foo\":\"bar\"}")) "foo")
```
3. map in clojure
```clojure
(respond (matches (http-server 12306) (json {:code 1 :message "message"})) "foo")
```

## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

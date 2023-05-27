(ns helix-init.test-runner
  (:require ["global-jsdom" :as global-jsdom]
            ["@testing-library/react" :as tlr]
            [cljs.test :as test]))

(defn teardown [f]
  (f)
  (tlr/cleanup))

(test/use-fixtures :each teardown)

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn test-runner []
  (global-jsdom)
  (test/run-all-tests #".*-test$"))

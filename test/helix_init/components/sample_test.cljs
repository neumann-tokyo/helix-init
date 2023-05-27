(ns helix-init.components.sample-test
  (:require [cljs.test :refer (deftest is)]
            ["@testing-library/react" :refer [render]]
            [helix.core :refer [$]]
            [helix-init.components.sample :refer [sample]]))

(deftest header-nav-test
  (let [container (render ($ sample))
        div (.getByText container "This is a sample component.")]
    (is (= (type div) js/HTMLDivElement))
    (is (= (.-textContent div) "This is a sample component."))))

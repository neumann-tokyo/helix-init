(ns helix-init.components.header-nav-test
  (:require [cljs.test :refer (deftest is)]
            ["global-jsdom" :as global-jsdom]
            ["@testing-library/react" :refer [render screen]]
            [helix.core :refer [$]]
            [helix-init.components.header-nav :refer [header-nav]]))

(deftest header-nav-test
  (let [_ (global-jsdom)
        _ (render ($ header-nav))]
    (is (= (.queryByText screen "Sign In") 1))))

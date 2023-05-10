(ns helix-init.components.header-nav
  (:require [helix.core :refer [defnc $ <>]]
            [helix.dom :as d]
            ["react-router-dom" :as rrd]
            ["jotai" :refer [useAtom]]
            ["js-cookie" :as Cookies]
            [helix-init.atoms :refer [sign-in-token-atom]]))

(defnc header-nav []
  (let [[sign-in-token set-sign-in-token] (useAtom sign-in-token-atom)]
    (d/ul
     {:class-name "flex gap-4"}
     (d/li
      ($ rrd/Link {:to "/"} (if sign-in-token "Home" "Sign In")))
     (d/li
      (if sign-in-token
        ($ rrd/Link {:to "/about"} "About")
        ($ rrd/Link {:to "/sign-up"} "Sign Up")))
     (d/li
      (when sign-in-token
        (d/a {:href "#"
              :on-click (fn [e]
                          (.preventDefault e)
                          (set-sign-in-token nil)
                          (.remove Cookies "sign-in-token"))}
             "Sign Out"))))))

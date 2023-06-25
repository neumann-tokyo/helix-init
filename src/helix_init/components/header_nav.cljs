(ns helix-init.components.header-nav
  (:require [helix.core :refer [defnc $]]
            ["@chakra-ui/react" :refer [Flex Button]]
            ["react-router-dom" :as rrd]
            ["jotai" :refer [useAtom]]
            ["js-cookie" :as Cookies]
            [helix-init.atoms :refer [sign-in-token-atom]]))

(defnc header-nav []
  (let [[sign-in-token set-sign-in-token] (useAtom sign-in-token-atom)]
    ($ Flex {:gap "4"}
       ($ rrd/Link {:to "/"}
          (if sign-in-token "Home" "Sign In"))
       (if sign-in-token
         ($ rrd/Link {:to "/about"} "About")
         ($ rrd/Link {:to "/sign-up"} "Sign Up"))
       (when sign-in-token
         ($ Button {:colorScheme "gray"
                    :variant "outline"
                    :size "xs"
                    :onClick (fn [e]
                               (.preventDefault e)
                               (set-sign-in-token nil)
                               (.remove Cookies "sign-in-token"))}
            "Sign Out")))))

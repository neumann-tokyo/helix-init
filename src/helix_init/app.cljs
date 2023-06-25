(ns helix-init.app
  (:require [helix.core :refer [defnc $]]
            ["react" :as r]
            ["react-dom/client" :as rdom]
            ["react-router-dom" :as rrd]
            ["@chakra-ui/react" :refer [ChakraProvider]]
            ["jotai" :refer [useAtom]]
            [helix-init.layouts.base :as base]
            [helix-init.pages.sign-in :as sign-in]
            [helix-init.pages.about :as about]
            [helix-init.pages.sign-up :as sign-up]
            [helix-init.pages.top :as top]
            [helix-init.atoms :refer [sign-in-token-atom]]
            [helix-init.components.require-auth :refer [require-auth]]))

(defnc app []
  (let [[sign-in-token _] (useAtom sign-in-token-atom)]
    ($ ChakraProvider
       ($ rrd/BrowserRouter
          ($ rrd/Routes
             ($ rrd/Route {:path "/" :element ($ base/layout)}
                ($ rrd/Route {:index true :element (if sign-in-token
                                                     ($ require-auth top/top-page)
                                                     ($ sign-in/sign-in-page))})
                ($ rrd/Route {:path "sign-up" :element ($ sign-up/sign-up-page)})
                ($ rrd/Route {:path "about" :element ($ require-auth about/about-page)})))))))

(defonce root (rdom/createRoot (js/document.getElementById "app")))
(defn ^:export init []
  (.render root ($ r/StrictMode ($ app))))

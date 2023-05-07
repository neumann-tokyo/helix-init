(ns helix-init.app
  (:require [helix.core :refer [defnc $]]
            ["react" :as r]
            ["react-dom/client" :as rdom]
            ["react-router-dom" :as rrd]
            [helix-init.layouts.base :as base]
            [helix-init.pages.sign-in :as sign-in]
            [helix-init.pages.about :as about]))

(defnc app []
  ($ rrd/BrowserRouter
     ($ rrd/Routes
        ($ rrd/Route {:path "/" :element ($ base/layout)}
           ($ rrd/Route {:index true :element ($ sign-in/sign-in-page)})
           ($ rrd/Route {:path "about" :element ($ about/about-page)})))))

(defonce root (rdom/createRoot (js/document.getElementById "app")))
(defn ^:export init []
  (.render root ($ r/StrictMode ($ app))))

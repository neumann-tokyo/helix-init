(ns helix-init.app
  (:require [helix.core :refer [defnc $]]
            [helix.dom :as d]
            ["react" :as r]
            ["react-dom/client" :as rdom]
            ["react-router-dom" :as rrd]))

(defnc home-page []
  (d/div
   (d/h1 {:class-name "text-4xl"} "Home")))

(defnc about-page []
  (d/div
   (d/h1 {:class-name "text-4xl"} "About")))

(defnc layout []
  (d/div
   (d/ul
    (d/li
     ($ rrd/Link {:to "/"} "Home"))
    (d/li
     ($ rrd/Link {:to "/about"} "About")))
   (d/div ($ rrd/Outlet))))

(defnc app []
  ($ rrd/BrowserRouter
     ($ rrd/Routes
        ($ rrd/Route {:path "/" :element ($ layout)}
           ($ rrd/Route {:index true :element ($ home-page)})
           ($ rrd/Route {:path "about" :element ($ about-page)})))))

(defonce root (rdom/createRoot (js/document.getElementById "app")))
(defn ^:export init []
  (.render root ($ r/StrictMode ($ app))))

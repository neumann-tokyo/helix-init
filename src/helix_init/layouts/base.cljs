(ns helix-init.layouts.base
  (:require [helix.core :refer [defnc $]]
            [helix.dom :as d]
            ["react-router-dom" :as rrd]
            [helix-init.components.header-nav :refer [header-nav]]))

(defnc layout []
  (d/div
   {:class-name "w-4/5 mx-auto"}
   ($ header-nav)
   (d/div ($ rrd/Outlet))))

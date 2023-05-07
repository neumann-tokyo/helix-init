(ns helix-init.components.header-nav
  (:require [helix.core :refer [defnc $]]
            [helix.dom :as d]
            ["react-router-dom" :as rrd]))

(defnc header-nav []
  (d/ul
   {:class-name "flex gap-4"}
   (d/li
    ($ rrd/Link {:to "/"} "Home"))
   (d/li
    ($ rrd/Link {:to "/about"} "About"))))

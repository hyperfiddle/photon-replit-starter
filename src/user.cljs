(ns user
  (:require [app]
            [hyperfiddle.photon :as p]
            [hyperfiddle.photon-dom :as dom])
  (:import [hyperfiddle.photon Pending]))

(p/defn App []
  (binding [dom/node (dom/by-id "root")]
    (p/remote (app/Todo-list.))))

(def main (p/client (p/main (try (App.)
                                 (catch Pending _)))))
(def reactor)
(defn ^:dev/after-load start! [] (set! reactor (main js/console.log js/console.error)))
;; TODO: keep seeing `missionary.Cancelled {message: 'Watch cancelled.'}` on the js console
(defn ^:dev/before-load stop! [] (when reactor (reactor)) (set! reactor nil))


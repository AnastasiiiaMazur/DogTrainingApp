package com.cmps.dogtrainingapp.ui.navigation

object Routes {
    const val DASHBOARD = "dashboard"
    const val TRAINING_HUB = "training hub"
    const val PROFILE = "profile"
    const val HEALTH_HUB = "health hub"
    const val WALK_TRACKER = "walk tracker"

    const val COURSE = "course/{courseId}"
    fun course(courseId: String) = "course/$courseId"

    const val LESSON = "course/{courseId}/lesson/{lessonId}"
    fun lesson(courseId: String, lessonId: String) = "course/$courseId/lesson/$lessonId"

    const val ADD_EDIT_EVENT = "add_edit_event?eventId={eventId}"
    fun addEditEvent(eventId: Long? = null): String {
        return if (eventId == null) {
            "add_edit_event"
        } else {
            "add_edit_event?eventId=$eventId"
        }
    }

    const val ALL_EVENTS = "all events"
}
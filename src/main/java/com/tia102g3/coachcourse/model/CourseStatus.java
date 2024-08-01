package com.tia102g3.coachcourse.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * ClassName： CourseStatus
 * package：com.tia102g3.coachcourse.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/16 @{TIME}
 * @Version 1.0
 */
@Getter
@RequiredArgsConstructor
@ToString
public enum CourseStatus {
    NOT_REVIEWED(0, "尚未審核"),
    IN_PROGRESS(1, "進行中"),
    COMPLETED(2, "已完成"),
    CANCELLED(3, "已取消"),
    POSTPONED(4, "已延期");

    private final Integer status;
    private final String description;

    public static CourseStatus getCoursrStatusByInt(Integer status) {
        for (CourseStatus courseStatus : values()) {
            if (courseStatus.getStatus() == status) {
                return courseStatus;
            }
        }
        return null;
    }
}


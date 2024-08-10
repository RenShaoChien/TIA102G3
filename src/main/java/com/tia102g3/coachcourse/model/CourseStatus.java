package com.tia102g3.coachcourse.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CourseStatus {
    NOT_REVIEWED(0, "未審核"),
    IN_PROGRESS(1, "進行中"),
    COMPLETED(2, "已結束");

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
    public static CourseStatus fromDescription(String description) {
        for (CourseStatus status : CourseStatus.values()) {
            if (status.getDescription().equals(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("沒有定義的 " + description);
    }

    @Override
    public String toString() {
        return description;
    }
}


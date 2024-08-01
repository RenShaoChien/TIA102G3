package com.tia102g3.systemcourse.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * ClassName： SystemCourseLevel
 * package：com.tia102g3.systemcourse.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
@Getter
@RequiredArgsConstructor
@ToString
public enum SystemCourseLevel {
    EASY(1, "初級"),
    MEDIUM(2, "中級"),
    HARD(3, "高級"),
    VERY_HARD(4, "最高級");

    private final Integer level;
    private final String description;

    public static SystemCourseLevel getSystemCourseLevelByInt(Integer level) {
        for (SystemCourseLevel systemCourseLevel : values()) {
            if (systemCourseLevel.getLevel().equals(level)) {
                return systemCourseLevel;
            }
        }
        return null;
    }
}

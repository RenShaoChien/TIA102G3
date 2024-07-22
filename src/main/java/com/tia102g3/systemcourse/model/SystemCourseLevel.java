package com.tia102g3.systemcourse.model;

/**
 * ClassName： SystemCourseLevel
 * package：com.tia102g3.systemcourse.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
public enum SystemCourseLevel {
    EASY(1),
    MEDIUM(2),
    HARD(3),
    VERY_HARD(4);

    private final Integer level;
    SystemCourseLevel(Integer level) {
        this.level = level;
    }
    public Integer getLevel() {
        return level;
    }
    public static SystemCourseLevel getSystemCourseLevelByInt(Integer level) {
        for (SystemCourseLevel systemCourseLevel : SystemCourseLevel.values()) {
            if (systemCourseLevel.getLevel().equals(level)) {
                return systemCourseLevel;
            }
        }
        return null;
    }
}

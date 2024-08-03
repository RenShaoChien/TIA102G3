package com.tia102g3.coachmember.model;

import java.util.List;

/**
 * ClassName： CoachMemberDAO
 * package：com.tia102g3.coachmember.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/27 @{TIME}
 * @Version 1.0
 */
public interface CoachMemberDAO {
    int insetCoachMember(CoachMember coachMember) throws Exception;
    int updateCoachMember(CoachMember coachMember) throws Exception;
    CoachMember getCoachMemberById(Integer cMemberID) throws Exception;
    int deleteCoachMember(Integer cMemberID) throws Exception;
    List<CoachMember> getAllCoachMembersList() throws Exception;
}

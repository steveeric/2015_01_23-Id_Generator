package jp.pmw.test;

import jp.pmw.id_generator.IdGenerator;
import jp.pmw.id_generator.IdGenerator_1;


public class Test {

	public static final String MEXT_NUMBER = "306058";
	public Test(){
		IdGenerator_1 generator = new IdGenerator_1(MEXT_NUMBER);

		String campusId = generator.generateCampuId(0);

		System.out.println("キャンパスID:"+campusId);

		generator.setCampusId(campusId);

		String year = "15";

		String studentId = generator.generateStudentId(year, 0);
		String studentId1 = generator.generateStudentId(year, 1);
		System.out.println("学生0:"+studentId+" 学生1:"+studentId1);

		String staffId = generator.generateStaffId(year, 0);
		String staffId1 = generator.generateStaffId(year, 1);
		System.out.println("職員0:"+staffId+" 職員1:"+staffId1);

		/*場所関係*/
		//建物ID
		String buildingId = generator.generateBuildingId(0);
		String buildingId1 = generator.generateBuildingId(1);
		System.out.println("建物0:"+buildingId+"建物1:"+buildingId1);

		//教室ID
		String roomId = generator.generateRoomId(0);
		String roomId1 = generator.generateRoomId(1);
		System.out.println("教室0:"+roomId+" 教室1:"+roomId1);

		//座席ID
		String seatId = generator.generateSeatId(roomId, 1, 1);
		System.out.println("教室0のSEAT_ID:"+seatId);

		String seatId1 = generator.generateSeatId(roomId1, 1, 1);
		System.out.println("教室0のSEAT_ID:"+seatId1);

		//授業関係
		String timetableId = generator.generateTimetableId(year, 0);
		String subjectId = generator.generateSubjectId(year, 0);
		String classId = generator.generateClassId(subjectId, year, "05", "23", timetableId);
		System.out.println("時間帯ID:"+timetableId+" 科目ID:"+subjectId+" 授業ID:"+classId);

	}

}

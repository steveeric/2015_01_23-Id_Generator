package jp.pmw.id_generator;


public class IdGenerator{

	private static final long serialVersionUID = 1L;

	/**IDの先頭につける**/
	//職員IDを識別するための英字(先頭につける)
	private static final String IDENTIFY_STAFF_ID = "STAFF";
	//学科IDを識別するための英字(先頭につける)
	private static final String IDENTIFY_DEPT_ID = "DEPT";
	//教室IDを識別するための英字(先頭につける)
	private static final String IDENTIFY_ROOM_ID = "ROOM";
	/**IDの先頭につける**/

	/**IDの後方につける*/
	//座席IDを識別するための英字(IDのおしりにつける)
	private static final String IDENTIFY_SEAT_ID = "SE";
	//学年歴IDを識別するための英字(IDのおしりにつける)
	private static final String IDENTIFY_ACADEMIC_CALENDAR_ID = "AC";
	//時限IDを識別するための英字(IDのおしりにつける)
	private static final String IDENTIFY_TIMETABLE_ID = "TT";
	//科目IDを識別するための英字(IDのおしりにつける)
	private static final String IDENTIFY_SUBJECT_ID = "SU";
	//学生IDを識別するための英字(IDのおしりにつける)
	private static final String IDENTIFY_STUDENT_ID = "ST";
	//教員IDを識別するための英字(IDのおしりにつける)
	private static final String IDENTIFY_FACULTY_ID = "FA";
	/**IDの後方につける*/

	//教職員の桁数4桁
	private static final String REVISTION_DIGIT_STAFF_ID = "%04d";
	//教室IDの補正桁数
	private static final String REVISTION_DIGIT_ROOM_ID = "%04d";
	//時間IDの桁補正
	private static final String REVISTION_DIGIT_TIMETABLE_ID = "%02d";
	//科目IDの補正桁数
	private static final String REVISTION_DIGIT_SUBJECT_ID = "%04d";
	//学生IDの登録順桁数
	private static final String REVISTION_DIGIT_STUDENT_ID = "%04d";
	//教員IDの登録順桁数
	private static final String REVISTION_DIGIT_FACULTY_ID = "%04d";

	//文科省大学番号
	private static String mextNumber;

	private static final class SingletonHolder {
		private static final IdGenerator instance = new IdGenerator();
	}


	public static IdGenerator getInstance() {
		return SingletonHolder.instance;
	}

	private Object readResolve() {
		return SingletonHolder.instance;
	}
	/**
	 * createdate : 2015年1月29日
	 * setCampusIdメソッド
	 * キャンパスIDをセットする
	 * @parm campusId キャンパスI
	 **/
	public static void setMextUniversity(String mextNumber){
		IdGenerator.mextNumber = mextNumber;
	}



	/**
	 * createdate : 2015年1月23日
	 * lastchange : 2015年2月10日
	 * generateStaffIdメソッド
	 * 職員IDを生成する
	 *
	 * 職員ID char(21)
	 *	・STAFF
	 *  ・文科省大学番号(6)
	 *  ・部(2)
	 *  ・課(2)
	 *  ・登録年度(2)
	 *  ・登録順(4)
	 *
	 * @parm division 部署番号(2桁)
	 * @parm department 所属課番号(2桁)
	 * @parm year 登録年度(2桁)
	 * @parm number 番号
	 * @return 教職員ID
	 **/
	public static String generateStaffId(String division,String department,String year,int number){
		String rivStaff = revistion(REVISTION_DIGIT_STAFF_ID,number);
		String addYearStaff = year + rivStaff;

		return IDENTIFY_STAFF_ID + getMextNumber() + division + department + addYearStaff;
	}

	/**
	 * createdate : 2015年2月10日
	 * generateDeptIdメソッド
	 * 学科IDを生成する
	 *
	 * 学科ID char(17)
	 * ・文科省大学番号(6)
	 * ・教育機関区分(1)
	 * ・大分類(2)
	 * ・中分類(2)
	 * ・小分類(2)
	 *
	 * @parm eduationSection 教育機関名
	 * @parm largeCategory 大分類
	 * @parm middleCategory 中分類
	 * @parm smallCategory 小分類
	 * @return 学科ID
	 **/
	public static String generateDeptId(String eduationSection,String largeCategory,String middleCategory,String smallCategory){
		return IDENTIFY_DEPT_ID + getMextNumber() + eduationSection + largeCategory + middleCategory + smallCategory;
	}





	/**
	 * createdate : 2015年1月23日
	 * generateStudentIdメソッド
	 * 学生IDを生成する
	 * @parm year 登録年度(2桁)
	 * @parm index 番号
	 * @return 学生ID
	 **/
	public static String generateStudentId(String year,int index){
		String rivStudent = revistion(REVISTION_DIGIT_STUDENT_ID,index);
		String addYearStudent = year + rivStudent;
		return createId(addYearStudent);
	}

	/**
	 * createdate : 2015年1月23日
	 * generateSeatIdメソッド
	 * 座席IDを生成する
	 * @parm roomId 教室ID
	 * @parm roomMapCellRow 教室マップ行番号
	 * @parm roomMapCellColumn 教室マップ列番号
	 * @return 座席ID
	 **/
	public static String generateSeatId(String roomId,int roomMapCellRow,int roomMapCellColumn){
		String rivCellRow = revistion(REVISTION_DIGIT_ROOM_MAP,roomMapCellRow);
		String rivCellColumn = revistion(REVISTION_DIGIT_ROOM_MAP,roomMapCellColumn);
		String seatId = roomId + rivCellRow + rivCellColumn;
		return seatId;
	}

	/**
	 * createdate : 2015年1月23日
	 * generateRoomIdメソッド
	 * 教室IDを生成する.
	 * @parm index 登録順番号
	 * @return 教室ID
	 **/
	public static String generateRoomId(int index){
		String rivRoomId = revistion(REVISTION_DIGIT_ROOM_ID,index);
		return createId(rivRoomId);
	}

	/**
	 * createdate : 2015年1月23日
	 * generateBuildingIdメソッド
	 * 建物IDを生成する.
	 * @parm index 登録順番号
	 * @return 建物ID
	 **/
	public static String generateBuildingId(int index){
		String rivBuilding = revistion(REVISTION_DIGIT_BUILDING_ID,index);
		return createId(rivBuilding);
	}

	/**
	 * createdate : 2015年1月23日
	 * generateCampuIdメソッド
	 * キャンパスIDを生成する.
	 * @parm index 登録順番号
	 * @return キャンパスID
	 **/
	public static String generateCampuId(int index){
		String rivCampus = revistion(REVISITON_DIGIT_CAMPUS_ID,index);
		String campusId = mextNumber + rivCampus;
		setCampusId(campusId);
		return campusId;
	}

	/**
	 * createdate : 2015年1月23日
	 * generateTimetableIdメソッド
	 * 時間帯IDを生成する.
	 * @parm year 登録年度(2桁)
	 * @parm index 登録順番号
	 * @return 時間帯ID
	 **/
	public static String generateTimetableId(String year,int index){
		String rivTimetable = revistion(REVISTION_DIGIT_TIMETABLE_ID,index);
		String addYearTimetable = year + rivTimetable;
		return createId(addYearTimetable);
	}

	/**
	 * createdate : 2015年1月23日
	 * generateSubjectIdメソッド
	 * 科目IDを生成する.
	 * @parm year 登録年度(2桁)
	 * @parm index 登録順番号
	 * @return 科目ID
	 **/
	public static String generateSubjectId(String year,int index){
		String rivSubject = revistion(REVISTION_DIGIT_SUBJECT_ID,index);
		String addYearSubject = year + rivSubject;
		return createId(addYearSubject);
	}

	/**
	 * createdate : 2015年1月23日
	 * generateClassIdメソッド
	 * 授業IDを生成する.
	 * @parm subjectId 科目ID
	 * @parm year 登録年度(2桁)
	 * @parm month 月(2桁)
	 * @parm day h(2桁)
	 * @parm timetableId 実施時間帯ID
	 * @return 授業ID
	 **/
	public static String generateClassId(
			String subjectId
			,String year
			,String month
			,String day
			,String timetableId){

		return subjectId + year + month + day + timetableId;

	}

	/**
	 * createdate : 2015年1月23日
	 * getMextNumberメソッド
	 * 大学文科省番号を返します.
	 * @parm str 文字列
	 **/
	private  static String getMextNumber(){
		if (mextNumber == null) {
			throw new NullPointerException("You not set 「MEXT_NUMBER」 !");
		}
		return mextNumber;
	}
	/**
	 * createdate : 2015年1月23日
	 * revistionメソッド
	 * 登録順の桁数を補正して返す
	 * @parm riv 補正情報
	 * @parm index 番号
	 **/
	private static String revistion(String riv,int index){
		return String.format(riv, index);
	}

}


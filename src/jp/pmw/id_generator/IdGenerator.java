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
	//教室マップセルの補正桁数
	private static final String REVISTION_DIGIT_ROOM_MAP = "%02d";
	//時間IDの桁補正
	private static final String REVISTION_DIGIT_TIMETABLE_ID = "%02d";
	//科目IDの補正桁数
	private static final String REVISTION_DIGIT_SUBJECT_ID = "%04d";
	//学生IDの登録順桁数
	private static final String REVISTION_DIGIT_STUDENT_ID = "%04d";
	//教員IDの登録順桁数
	private static final String REVISTION_DIGIT_FACULTY_ID = "%04d";
	//日付の補正桁数
	private static final String REVISTION_DIGIT_DATE_ID = "%02d";

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
	 * ・DEPUT
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
	 * lastchange : 2015年2月10日
	 * generateRoomIdメソッド
	 * 教室IDを生成する.
	 *
	 * 教室ID char (14)
	 * ・ROOM
	 * ・文科省大学番号(6)
	 * ・登録順(4)
	 * @parm number 登録順番号
	 * @return 教室ID
	 **/
	public static String generateRoomId(int number){
		String rivRoomId = revistion(REVISTION_DIGIT_ROOM_ID,number);
		return IDENTIFY_ROOM_ID + getMextNumber() + rivRoomId;
	}


	/**
	 * createdate : 2015年1月23日
	 * lastchange : 2015年2月10日
	 * generateSeatIdメソッド
	 * 座席IDを生成する
	 *
	 * 座席ID(20)
	 * ・教室ID(14)
	 * ・教室マップセル行(2)
	 * ・教室マップセル列(2)
	 *  ・SE (2)識別英字
	 * @parm roomId 教室ID
	 * @parm roomMapCellRow 教室マップ行番号
	 * @parm roomMapCellColumn 教室マップ列番号
	 * @return 座席ID
	 **/
	public static String generateSeatId(String roomId,int roomMapCellRow,int roomMapCellColumn){
		String rivCellRow = revistion(REVISTION_DIGIT_ROOM_MAP,roomMapCellRow);
		String rivCellColumn = revistion(REVISTION_DIGIT_ROOM_MAP,roomMapCellColumn);
		return roomId + rivCellRow + rivCellColumn + IDENTIFY_SEAT_ID;
	}



	/**
	 * createdate : 2015年2月10日
	 * generateAcademicCalendarIdメソッド
	 * 学年歴IDを生成する
	 *
	 * 学年歴ID  char (26)
	 * ・学科ID(17)
	 * ・年(2)
	 * ・月(2)
	 * ・日(2)
	 * ・ AC(2) 学年歴識別英字
	 *
	 * @parm year 登録年度(2桁)
	 * @parm index 番号
	 * @return 学生ID
	 **/
	public static String generateAcademicCalendarId(String deptId,String year,String month,String day){
		return deptId + revisionDate(year) + revisionDate(month) + revisionDate(day) + IDENTIFY_ACADEMIC_CALENDAR_ID;

	}

	/**
	 * createdate : 2015年2月10日
	 * revisionDateメソッド
	 * 日付に関する文字列を2桁に補正する.
	 * @return 日付
	 **/
	private static String revisionDate(String str){
		if(str.length() == 1){
			str = "0" + str;
		}
		return str;
	}


	/**
	 * createdate : 2015年1月23日
	 * lastchange : 2015年2月10日
	 * generateTimetableIdメソッド
	 * 時限IDを生成する.
	 *
	 * 時限ID char (25)
	 * ・学科ID(17)
	 * ・登録年度(2)
	 * ・登録順(4)
	 * ・SU(2)科目識別英字
	 *
	 * @parm deptId 学科ID
	 * @parm year 登録年度(2桁)
	 * @parm number 登録順番号
	 * @return 時限ID
	 **/
	public static String generateTimetableId(String deptId,String year,int number){
		String rivTimetable = revistion(REVISTION_DIGIT_TIMETABLE_ID,number);
		String addYearTimetable = year + rivTimetable;
		return deptId + addYearTimetable + IDENTIFY_TIMETABLE_ID;
	}

	/**
	 * createdate : 2015年1月23日
	 * lastchange : 2015年2月10日
	 * generateSubjectIdメソッド
	 * 科目IDを生成する.
	 *
	 * 科目ID(25)
	 * ・学科ID(17)
	 * ・登録年度(2)
	 * ・登録順(4)
	 * ・SU(2) 科目ID識別英字
	 *
	 * @parm deptId 学科ID
	 * @parm year 登録年度(2桁)
	 * @parm number 登録順番号
	 * @return 科目ID
	 **/
	public static String generateSubjectId(String deptId, String year, int number){
		String rivSubject = revistion(REVISTION_DIGIT_SUBJECT_ID,number);
		String addYearSubject = year + rivSubject;
		return deptId + addYearSubject + IDENTIFY_SUBJECT_ID;
	}

	/**
	 * createdate : 2015年1月23日
	 * lastchange : 2015年2月10日
	 * generateStudentIdメソッド
	 * 学生IDを生成する
	 *
	 * 学生ID(25)
	 * ・学科ID(17)
	 * ・登録年度(2)
	 * ・登録順(4)
	 * ・ST(2)学生ID識別英字
	 *
	 * @param deptId 学科ID
	 * @parm year 登録年度(2桁)
	 * @parm number 番号
	 * @return 学生ID
	 **/
	public static String generateStudentId(String deptId,String year,int number){
		String rivStudent = revistion(REVISTION_DIGIT_STUDENT_ID,number);
		String addYearStudent = year + rivStudent;
		return deptId + addYearStudent + IDENTIFY_STUDENT_ID;
	}


	/**
	 * createdate : 2015年2月10日
	 * generateFacultyIdメソッド
	 * 教員IDを生成する
	 *
	 * 教員ID(25)
	 * ・学科ID(17)
	 * ・登録年度(2)
	 * ・登録順(4)
	 * ・FA(2)教員ID識別英字
	 *
	 * @param deptId 学科ID
	 * @parm year 登録年度(2桁)
	 * @parm number 番号
	 * @return 学生ID
	 **/
	public static String generateFacultyId(String deptId,String year,int number){
		String rivFaculty = revistion(REVISTION_DIGIT_FACULTY_ID,number);
		String addYearFaculty = year + rivFaculty;
		return deptId + addYearFaculty + IDENTIFY_FACULTY_ID;
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


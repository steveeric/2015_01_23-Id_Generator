package jp.pmw.id_generator;


/**
 * createdate : 2015年1月22日
 * IdGeneratorクラス
 * 授業運営管理システムで使用するIDを
 * 生成するクラスです.
 *
 * @author Shota Ito.
 * @version 1.0.0
 */
public class IdGenerator_2 {
	private static final long serialVersionUID = 1L;
	//教職員の桁数4桁
	private static final String REVISTION_DIGIT_STAFF_ID = "%04d";
	//学生IDの登録順桁数
	private static final String REVISTION_DIGIT_STUDENT_ID = "%05d";
	//時間IDの桁補正
	private static final String REVISTION_DIGIT_TIMETABLE_ID = "%02d";
	//教室IDの補正桁数
	private static final String REVISTION_DIGIT_ROOM_ID = "%04d";
	//建物IDの補正桁数
	private static final String REVISTION_DIGIT_BUILDING_ID = "%03d";
	//キャンパスIDの補正桁数
	private static final String REVISITON_DIGIT_CAMPUS_ID = "%02d";
	//科目IDの補正k多数
	private static final String REVISTION_DIGIT_SUBJECT_ID = "%05d";
	//教室マップのセル番号桁補正
	private static final String REVISTION_DIGIT_ROOM_MAP = "%02d";

	//文科省大学番号
	private static String mextNumber;
	//キャンパスID
	private static String campusId;


	private static final class SingletonHolder {
		private static final IdGenerator_2 instance = new IdGenerator_2();
	}


	public static IdGenerator_2 getInstance() {
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
		IdGenerator_2.mextNumber = mextNumber;
	}

	/**
	* createdate : 2015年1月23日
	* setCampusIdメソッド
	* キャンパスIDをセットする
	* @parm campusId キャンパスI
	**/
	public static void setCampusId(String campusId){
		IdGenerator_2.campusId = campusId;
	}
	/**
	* createdate : 2015年1月26日
	* getCampusIdメソッド
	* キャンパスIDを返す
	* @return キャンパスID
	**/
	public static String getCampusId(){
		return campusId;
	}


	/**
	* createdate : 2015年1月23日
	* generateStaffIdメソッド
	* 教職員IDを生成する
	* @parm year 登録年度(2桁)
	* @parm index 番号
	* @return 教職員ID
	**/
	public static String generateStaffId(String year,int index){
		String rivStaff = revistion(REVISTION_DIGIT_STAFF_ID,index);
		String addYearStaff = year + rivStaff;
		return createId(addYearStaff);
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
	* createIdメソッド
	* キャンパスIDを先頭文字につけて返す.
	* @parm str 文字列
	**/
	private  static String createId(String str){
		if (campusId == null) {
			throw new NullPointerException("You not set 「CAMPUS_ID」 !");
		}
		return campusId + str;
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


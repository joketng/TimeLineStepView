package com.joketng.timelinestepview.bean

import com.joketng.timelinestepview.TimeLineState

/**
 * @Description:
 * @Author:  joketng
 * @Email:  joketng@163.com
 * @Time:  2018/11/1
 */
open class BaseBean @JvmOverloads constructor(open var leftTitle: String = "",
                                              open var leftTime: String = "",
                                              open var rightTitle: String = "",
                                              open var rightTime: String = "",
                                              open var timeLineState: TimeLineState = TimeLineState.ACTIVE)
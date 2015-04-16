//
//  FECustomer.h
//  SmartHome
//
//  Created by Seven on 15-4-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"

@interface FECustomer : FEBaseResponse

//private int userID; 			//用户id
//private String customerName;	//用户姓名
//private String phone;			//用户手机号码
//private String addr;			//用户住址
//private String email;			//用户邮箱
//private int status;
@property (nonatomic, strong) NSNumber *userID;
@property (nonatomic, strong) NSString *customerName;
@property (nonatomic, strong) NSString *phone;
@property (nonatomic, strong) NSString *addr;
@property (nonatomic, strong) NSString *email;
@property (nonatomic, strong) NSNumber *status;

@end

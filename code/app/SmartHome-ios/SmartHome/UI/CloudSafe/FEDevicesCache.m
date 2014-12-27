//
//  FEDevicesCache.m
//  SmartHome
//
//  Created by Seven on 14-12-26.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEDevicesCache.h"
#import "FESensor.h"
#import "AppDelegate.h"
#import "FECoreDataHandler.h"
#import "FESensor.h"
#import "CDUser.h"
#import "FEUserMarkResponse.h"
#import "FEMarkRequest.h"
#import "FEPage.h"
#import "FEWebServiceManager.h"
#import "FESensorListResponse.h"

@interface FEDevicesCache ()

@property (nonatomic, strong) NSArray *alldevices;
@property (nonatomic, strong) NSArray *allmarks;

@end

@implementation FEDevicesCache

+(FEDevicesCache *)sharedInstance{
    static FEDevicesCache *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = (FEDevicesCache *)[[self alloc] init];
    });
    return instance;
}


-(void)getAllMarks:(void (^)(NSArray *items))block{
    if (self.allmarks) {
        block(self.allmarks);
    }else{
        [self requestMarks:block];
    }
}

-(void)getAlldevices:(void (^)(NSArray *items))block{
    if (self.alldevices) {
        block(_allmarks);
    }else{
        [self requestSensor:block];
    }
}

-(void)getFilterSensors:(void (^)(NSArray *items))block{
    
    [self getAlldevices:^(NSArray *marks) {
        NSMutableArray *sensors = [NSMutableArray array];
        NSArray *sensorList = [_alldevices filteredArrayUsingPredicate:[NSPredicate predicateWithFormat:@"SELF.sensorKind == %d || SELF.sensorKind == %d",CONTIUOUS_SENSOR,DISCRETE_SENSOR]];
        if (sensorList.count) {
            NSArray *allmarks = [sensorList valueForKey:@"mark"];
            NSSet *set = [NSSet setWithArray:allmarks];
            NSArray *marks = set.allObjects;
            
            for (NSString *mark in marks) {
                [sensors addObject:@{__SENSOR_MARK:mark,__SENSOR_LIST:[sensorList filteredArrayUsingPredicate:[NSPredicate predicateWithFormat:@"SELF.mark == %@",mark]]}];
            }
        }
        block(sensors);
    }];
    
}

-(void)getFilterControlDevice:(void (^)(NSArray *items))block{
    [self getAlldevices:^(NSArray *marks) {
        NSMutableArray *sensors = [NSMutableArray array];
        NSArray *sensorList = [_alldevices filteredArrayUsingPredicate:[NSPredicate predicateWithFormat:@"SELF.sensorKind == %d",CTRL_SENSOR]];
        if (sensorList.count) {
            NSArray *allControlPoint = [sensorList valueForKey:@"sensorTypeName"];
            NSSet *set = [NSSet setWithArray:allControlPoint];
            NSArray *sensorTypeName = set.allObjects;
            
            for (NSString *typename in sensorTypeName) {
                [sensors addObject:@{__SENSOR_MARK:typename,__SENSOR_LIST:[sensorList filteredArrayUsingPredicate:[NSPredicate predicateWithFormat:@"SELF.sensorTypeName == %@",typename]]}];
            }
        }
        block(sensors);
    }];
    
}

-(void)requestSensor:(void (^)(NSArray *items))block{
    FEPage *page = [[FEPage alloc] initWithPageSize:0 currentPage:0 count:0];
    FESensorListRequest *request = [[FESensorListRequest alloc] initWithUserID:FELoginUser.userid page:page attributes:nil];
    [[FEWebServiceManager sharedInstance] sensorList:request response:^(NSError *error, FESensorListResponse *response) {
        if (!error && response.result.errorCode.integerValue == 0) {
            _alldevices = response.sensorList;
            block(_alldevices);
        }else{
            block(nil);
        }
    }];
}

-(void)requestMarks:(void (^)(NSArray *items))block{
    FEPage *page = [[FEPage alloc] initWithPageSize:0 currentPage:0 count:0];
    FEMarkRequest *rdata = [[FEMarkRequest alloc] initWithUserid:FELoginUser.userid page:page];
    [[FEWebServiceManager sharedInstance] markList:rdata response:^(NSError *error, FEUserMarkResponse *response) {
        if (!error && response.result.errorCode.integerValue == 0) {
            _allmarks = response.markList;
            block(_allmarks);
        }else{
            block(nil);
        }
    }];
}

@end

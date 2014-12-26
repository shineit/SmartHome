//
//  FEDevicesCache.m
//  SmartHome
//
//  Created by Seven on 14-12-26.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEDevicesCache.h"

@interface FEDevicesCache ()

@property (nonatomic, strong) NSArray *alldevices;

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

-(void)putDevices:(NSArray *)devices{
    _alldevices = devices;
}

-(NSArray *)getAlldevices{
    return _alldevices;
}

-(NSArray *)getFilterSensors{
    
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
    return sensors;
}

-(NSArray *)getFilterControlDevice{
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
    return sensors;
}

@end

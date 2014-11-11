//
//  FEWarringTableViewCell.m
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEWarringTableViewCell.h"
#import "FEAlarm.h"
#import "FEEnumString.h"

@interface FEWarringTableViewCell (){
//    NSArray *_alarmTypestrings;
    NSArray *_alarmTypeimages;
//    NSArray *_handleStatus;
//    NSArray *_alarmDevices;
}

@end

@implementation FEWarringTableViewCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
//        _alarmTypestrings = [NSArray arrayWithObjects:FEString(@"UNKNOW"),FEString(@"OFFLINE_ALARM"),FEString(@"FAULT_ALARM"),FEString(@"SUBPRESSURE_ALARM"),FEString(@"WARN_ALARM"),FEString(@"ERROR_ALARM"),FEString(@"FEEDBACK_ALARM"),FEString(@"ACTION_ALARM"),FEString(@"RESET_ALARM"),FEString(@"SETUP_ALARM"),FEString(@"REMOVE_ALARM"), nil];
//        _alarmDevices = [NSArray arrayWithObjects:FEString(@"CONCENTRATOR_ALARM"),FEString(@"HOME_SENSOR"),FEString(@"FIRE_SENSOR"), nil];
//        _handleStatus = [NSArray arrayWithObjects:FEString(@"NONE_CLEAR"),FEString(@"MANUAL_CLEAR"),FEString(@"AUTO_CLEAR"), nil];
        
        [self setup];
    }
    return self;
}

-(void)setup{
    _typeImageView = [[UIImageView alloc] initWithFrame:CGRectMake(5, 13, 20, 20)];
    _typeImageView.image = [UIImage imageFromColor:[UIColor redColor]];
    [self.contentView addSubview:_typeImageView];
    
    _typeLabel = [[UILabel alloc] initWithFrame:CGRectMake(30, 13, 70, 20)];
    _typeLabel.backgroundColor = [UIColor clearColor];
    _typeLabel.text = @"预警";
    [self.contentView addSubview:_typeLabel];
    
    _deviceLabel = [[UILabel alloc] initWithFrame:CGRectMake(105, 13, 120, 20)];
    _deviceLabel.backgroundColor = [UIColor clearColor];
    _deviceLabel.text = @"烟雾报警器";
    [self.contentView addSubview:_deviceLabel];
    
    _statLabel = [[UILabel alloc] initWithFrame:CGRectMake(230, 5, 80, 20)];
    _statLabel.backgroundColor = [UIColor clearColor];
    _statLabel.textAlignment = NSTextAlignmentRight;
    _statLabel.text = @"未处理";
    [self.contentView addSubview:_statLabel];
    
    _timeLabel = [[UILabel alloc] initWithFrame:CGRectMake(230, 30, 90, 20)];
    _timeLabel.font = [UIFont systemFontOfSize:12];
    _timeLabel.backgroundColor = [UIColor clearColor];
    _timeLabel.textAlignment = NSTextAlignmentCenter;
    _timeLabel.text = @"00/00/00/00";
    [self.contentView addSubview:_timeLabel];
    
}

-(void)configWithAlarm:(FEAlarm *)alarm{
    _typeLabel.text = [FEEnumString alarmType:alarm.alarmType];
    _deviceLabel.text = [FEEnumString deviceType:alarm.objType];
    _statLabel.text = [FEEnumString alarmHandledType:alarm.clearStatus];
    _timeLabel.text = [[NSDate dateWithTimeIntervalSince1970:alarm.alarmTime.longLongValue / 1000] defaultFormat];
}

- (void)awakeFromNib
{
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end

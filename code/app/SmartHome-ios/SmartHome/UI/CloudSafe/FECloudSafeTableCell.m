//
//  FECloudSafeTableCell.m
//  SmartHome
//
//  Created by Seven on 14-10-25.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECloudSafeTableCell.h"
#import "FESensor.h"
#import "AppDelegate.h"

@interface FECloudSafeTableCell (){
    
}
@property (nonatomic, strong) UISwitch *deviceSwitch;

@end

@implementation FECloudSafeTableCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
        [self setup];
    }
    return self;
}

-(void)setup{
    _deviceSwitch = [[UISwitch alloc] initWithFrame:CGRectMake(0, 0, 60, 30)];
    [_deviceSwitch addTarget:self action:@selector(switchDidChange:) forControlEvents:UIControlEventValueChanged];
    self.accessoryView = _deviceSwitch;
}

-(void)switchDidChange:(UISwitch *)sw{
    if ([self.delegate respondsToSelector:@selector(switchStatusDidChange:switcher:)]) {
        [self.delegate switchStatusDidChange:self switcher:sw];
    }
}

- (void)awakeFromNib
{
    // Initialization code
}

-(void)setDeviceOpen:(BOOL)deviceOpen{
    [self.deviceSwitch setOn:deviceOpen];
}


-(void)configWithSensor:(FESensor *)sensor{
    _sensor = sensor;
    self.textLabel.text = sensor.sensorTypeName;
    self.deviceOpen = sensor.status.boolValue;
    self.imageView.image = [UIImage imageNamed:@"sensorSmoke"];
}

-(BOOL)isDeviceOpen{
    return YES;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end

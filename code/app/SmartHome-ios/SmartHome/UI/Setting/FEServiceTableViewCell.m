//
//  FEServiceTableViewCell.m
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEServiceTableViewCell.h"
#import "FEOrder.h"

@interface FEServiceTableViewCell ()

@property (nonatomic, strong) NSArray *typeArray;

@end

@implementation FEServiceTableViewCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
        [self setup];
        _typeArray = [NSArray arrayWithObjects:FEString(@"ORDER_INSTALL"),FEString(@"ORDER_REPAIR"),FEString(@"ORDER_CONSULT"), nil];
    }
    return self;
}

-(void)setup{
    
    self.backgroundView = [UIView new];
    self.backgroundView.backgroundColor = [UIColor whiteColor];
    
    _numberLabel = [[FELabel alloc] initWithFrame:CGRectMake(5, 20, 80, 20)];
    _numberLabel.textAlignment = NSTextAlignmentCenter;
//    _numberLabel.text = @"001";
    [self.contentView addSubview:_numberLabel];
    
    _typeLabel = [[FELabel alloc] initWithFrame:CGRectMake(95, 20, 115, 20)];
    _typeLabel.textAlignment = NSTextAlignmentCenter;
//    _typeLabel.text = @"安装服务";
    [self.contentView addSubview:_typeLabel];
    
    _statusLabel = [[FELabel alloc] initWithFrame:CGRectMake(225, 20, 90, 20)];
    _statusLabel.textAlignment = NSTextAlignmentCenter;
    _statusLabel.textColor = [UIColor orangeColor];
//    _statusLabel.text = @"待处理";
    [self.contentView addSubview:_statusLabel];
    
}

-(void)configWithOrder:(FEOrder *)order{
    self.numberLabel.text = order.orderID;
    _statusLabel.text = order.orderStatus.boolValue?FEString(@"ORDER_DEAL"):FEString(@"ORDER_WAIT");
    _typeLabel.text = _typeArray[order.orderType.integerValue];
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

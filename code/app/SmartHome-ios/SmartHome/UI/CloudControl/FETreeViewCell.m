//
//  FETreeViewCell.m
//  SmartHome
//
//  Created by Seven on 14-10-24.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FETreeViewCell.h"
#import "FEControlObject.h"
#import "FESensor.h"


@interface FETreeViewCell ()

@property (nonatomic, strong) FELabel *titleLabel;
@property (nonatomic, strong) UIImageView *indicatorView;
@property (nonatomic, strong) UIView *controllContentView;
@property (nonatomic, strong) FELabel *numberLabel;
//@property (nonatomic, strong) FELabel *numberLabelText;
@property (nonatomic, strong) FELabel *regionLabel;
//@property (nonatomic, strong) FELabel *regionLabelText;

@end

@implementation FETreeViewCell

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
    FELabel *title = [[FELabel alloc] initWithFrame:CGRectMake(0, 10, 200, 20)];
    self.titleLabel = title;
    [self.contentView addSubview:title];
    
    _indicatorView = [[UIImageView alloc] initWithFrame:CGRectMake(0, 0, 13, 8)];
    _indicatorView.image = [UIImage imageFromColor:[UIColor redColor]];
    _indicatorView.hidden = YES;
    self.accessoryView = _indicatorView;
    
    self.controllContentView = [[UIView alloc] initWithFrame:self.contentView.bounds];
    UILabel *label = [[FELabel alloc] initWithFrame:CGRectMake(5, 5, 60, 20)];
    label.text = FEString(@"编号:");
    [self.controllContentView addSubview:label];
    
    self.numberLabel = [[FELabel alloc] initWithFrame:CGRectMake(label.frame.origin.x + label.bounds.size.width + 5, 5, 80, 20)];
    [self.controllContentView addSubview:self.numberLabel];
    
    self.regionLabel = [[FELabel alloc] initWithFrame:CGRectMake(self.controllContentView.bounds.size.width - 80 - 10, 5, 80, 20)];
    [self.controllContentView addSubview:self.regionLabel];
    
    label = [[FELabel alloc] initWithFrame:CGRectMake(self.regionLabel.frame.origin.x - 60 - 5, 5, 60, 20)];
//    label.text = FEString(<#_S#>)
    [self.controllContentView addSubview:label];
    [self.contentView addSubview:self.controllContentView];
//    self.indentationLevel
}

-(void)configurelevel:(NSInteger)level withControlObj:(id)obj{
    
//    self.titleLabel.text = obj.name;
    
    if (level == 0) {
        [self.controllContentView setHidden:YES];
        self.titleLabel.hidden = NO;
        self.imageView.hidden = NO;
        self.titleLabel.text = obj[@"mark"];
        self.imageView.image = [UIImage imageNamed:@"doorControl"];
        self.indicatorView.hidden = NO;
        self.indicatorView.image = [UIImage imageNamed:@"controlIndicator"];
        self.backgroundColor = [UIColor whiteColor];
    } else if (level == 1) {
        [self.controllContentView setHidden:NO];
        self.imageView.hidden = YES;
        self.titleLabel.hidden = YES;
        self.numberLabel.text = [NSString stringWithFormat:@"%@",((FESensor *)obj).sensorID];
        self.regionLabel.text = ((FESensor *)obj).mark;
        self.indicatorView.hidden = YES;
        self.backgroundColor = [UIColor whiteColor];
    } else if (level >= 2) {
        self.indicatorView.hidden = YES;
        self.backgroundColor = [UIColor whiteColor];
    }
    
    CGFloat left = 45 + 20 * level;
    
    CGRect titleFrame = self.titleLabel.frame;
    titleFrame.origin.x = left;
    self.titleLabel.frame = titleFrame;
    
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

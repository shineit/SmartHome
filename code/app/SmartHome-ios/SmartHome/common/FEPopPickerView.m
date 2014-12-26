//
//  FEPopPickerView.m
//  popPicker
//
//  Created by Seven on 14-12-25.
//  Copyright (c) 2014年 Fuego. All rights reserved.
//

#import "FEPopPickerView.h"

#define kPopViewCornerRadius 4


@interface FEPopPickerView ()<UITableViewDataSource,UITableViewDelegate>{
    CGFloat _buttonHeight;
    NSArray *_buttonTitles;
    BOOL _isShowPopView;
    NSIndexPath *_indexPath;
}

@property (nonatomic, strong) UIView *popView;
@property (nonatomic, strong) UITableView *pickerTableView;
@property (nonatomic, strong) UIView *window;

@end

@implementation FEPopPickerView


-(id)initWithCoder:(NSCoder *)aDecoder{
    self = [super initWithCoder:aDecoder];
    if (self) {
        [self setup];
    }
    return self;
}

-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        [self setup];
    }
    return self;
}

-(void)setup{
    _selected = -1;
    _buttonHeight = 40;
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIDeviceOrientationDidChangeNotification object:nil];
    self.backgroundColor = [UIColor grayColor];
    UITapGestureRecognizer *rec = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(tapGes:)];
    [self addGestureRecognizer:rec];
    CAShapeLayer *layer = [self createIndicatorWithColor:[UIColor blackColor] andPosition:CGPointMake(self.bounds.size.width - 20, self.bounds.size.height / 2.0)];
    [self.layer addSublayer:layer];
    
    UILabel *label = [[UILabel alloc] initWithFrame:CGRectMake(2, (self.bounds.size.height - 20) / 2.0f, self.bounds.size.width - 2 - 40, 20)];
    [self addSubview:label];
    self.titleLabel = label;
    label.text = @"测试！";
    
}

-(void)tapGes:(UIGestureRecognizer *)ges{
    if (!_isShowPopView) {
        [self showPopView];
        _isShowPopView = YES;
    }
    
}

-(void)showPopView{
    
    if (!_window) {
        _window = [[UIView alloc] initWithFrame:CGRectMake(0, 0, [UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height)];
//        _window.userInteractionEnabled = YES;
//        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(dismiss)];
//        [_window addGestureRecognizer:tap];
    }
    
    _popView = [self createContentView];
    _popView.layer.shouldRasterize = YES;
    _popView.layer.rasterizationScale = [[UIScreen mainScreen] scale];
    
    _window.layer.shouldRasterize = YES;
    _window.layer.rasterizationScale = [[UIScreen mainScreen] scale];
    
    _popView.layer.opacity = 0.5f;
    _popView.layer.transform = CATransform3DMakeScale(1.3f, 1.3f, 1.0);
    
    _window.backgroundColor = [UIColor colorWithRed:0 green:0 blue:0 alpha:0];
    
    [_window addSubview:_popView];
    
    [[[[UIApplication sharedApplication] windows] firstObject] addSubview:_window];
    [UIView animateWithDuration:0.2f delay:0.0 options:UIViewAnimationOptionCurveEaseInOut
                     animations:^{
                         _window.backgroundColor = [UIColor colorWithRed:0 green:0 blue:0 alpha:0.5f];
                         _popView.layer.opacity = 1.0f;
                         _popView.layer.transform = CATransform3DMakeScale(1, 1, 1);
                     }
                     completion:NULL
     ];
}


- (void)dismiss
{
    CATransform3D currentTransform = _popView.layer.transform;
    
    CGFloat startRotation = [[_popView valueForKeyPath:@"layer.transform.rotation.z"] floatValue];
    CATransform3D rotation = CATransform3DMakeRotation(-startRotation + M_PI * 270.0 / 180.0, 0.0f, 0.0f, 0.0f);
    
    _popView.layer.transform = CATransform3DConcat(rotation, CATransform3DMakeScale(1, 1, 1));
    _popView.layer.opacity = 1.0f;
    
    [UIView animateWithDuration:0.2f delay:0.0 options:UIViewAnimationOptionTransitionNone
                     animations:^{
                         _window.backgroundColor = [UIColor colorWithRed:0.0f green:0.0f blue:0.0f alpha:0.0f];
                         _popView.layer.transform = CATransform3DConcat(currentTransform, CATransform3DMakeScale(0.6f, 0.6f, 1.0));
                         _popView.layer.opacity = 0.0f;
                     }
                     completion:^(BOOL finished) {
                         for (UIView *v in [_window subviews]) {
                             [v removeFromSuperview];
                             _popView = nil;
                         }
                         [_window removeFromSuperview];
                         _window = nil;
                         _isShowPopView = NO;
                     }
     ];
    
}


-(UIView *)createContentView{
    if (_pickerTableView == NULL) {
        _pickerTableView = [[UITableView alloc] initWithFrame:CGRectMake(0, 0, 300, 150)];
//        if (self.selected != -1) {
//            [_pickerTableView selectRowAtIndexPath:[NSIndexPath indexPathForRow:self.selected inSection:0] animated:NO scrollPosition:UITableViewScrollPositionMiddle];
//        }
        
        _pickerTableView.allowsSelectionDuringEditing = YES;
        _pickerTableView.dataSource = self;
        _pickerTableView.delegate = self;
        _pickerTableView.tableFooterView = [UIView new];
    }
    
    CGSize screenSize = [self getScreenSize];
    CGSize dialogSize = [self getPopViewSize];
    
    // This is the dialog's container; we attach the custom content and the buttons to this one
    UIView *dialogContainer = [[UIView alloc] initWithFrame:CGRectMake((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2, dialogSize.width, dialogSize.height)];
    dialogContainer.userInteractionEnabled = YES;
    
    //
    CAGradientLayer *gradient = [CAGradientLayer layer];
    gradient.frame = dialogContainer.bounds;
    gradient.colors = [NSArray arrayWithObjects:
                       (id)[[UIColor colorWithRed:255.0/255.0 green:255.0/255.0 blue:255.0/255.0 alpha:1.0f] CGColor],
                       (id)[[UIColor colorWithRed:255.0/255.0 green:255.0/255.0 blue:255.0/255.0 alpha:1.0f] CGColor],
                       (id)[[UIColor colorWithRed:255.0/255.0 green:255.0/255.0 blue:255.0/255.0 alpha:1.0f] CGColor],
                       nil];
    
    gradient.cornerRadius = kPopViewCornerRadius;
    [dialogContainer.layer insertSublayer:gradient atIndex:0];
    
    dialogContainer.layer.cornerRadius = kPopViewCornerRadius;
    //    dialogContainer.layer.borderColor = [[UIColor colorWithRed:198.0/255.0 green:198.0/255.0 blue:198.0/255.0 alpha:1.0f] CGColor];
    //    dialogContainer.layer.borderWidth = 1;
    dialogContainer.layer.shadowRadius = kPopViewCornerRadius + 5;
    dialogContainer.layer.shadowOpacity = 0.1f;
    dialogContainer.layer.shadowOffset = CGSizeMake(0 - (kPopViewCornerRadius + 5)/2, 0 - (kPopViewCornerRadius + 5)/2);
    dialogContainer.layer.shadowColor = [UIColor blackColor].CGColor;
    dialogContainer.layer.shadowPath = [UIBezierPath bezierPathWithRoundedRect:dialogContainer.bounds cornerRadius:dialogContainer.layer.cornerRadius].CGPath;
    
    // There is a line above the button
    UIView *lineView = [[UIView alloc] initWithFrame:CGRectMake(0, dialogContainer.bounds.size.height - _buttonHeight, dialogContainer.bounds.size.width, 0.5)];
    lineView.backgroundColor = [UIColor colorWithRed:185.0/255.0 green:185.0/255.0 blue:185.0/255.0 alpha:1.0f];
    [dialogContainer addSubview:lineView];
    // ^^^
    
    // Add the custom container if there is any
    [dialogContainer addSubview:_pickerTableView];
    dialogContainer.userInteractionEnabled = YES;
    // Add the buttons too
    [self addButtonsToView:dialogContainer];
    
    
//    CGFloat buttonWidth = dialogContainer.bounds.size.width / _buttonTitles.count;
//    for (int i = 0; i < _buttonTitles.count - 1; i++) {
//        UIView *lineView = [[UIView alloc] initWithFrame:CGRectMake(buttonWidth * (i + 1), dialogContainer.bounds.size.height - _buttonHeight, 0.5, _buttonHeight)];
//        lineView.backgroundColor = [UIColor colorWithRed:185.0/255.0 green:185.0/255.0 blue:185.0/255.0 alpha:1.0f];
//        [dialogContainer addSubview:lineView];
//    }
    
    return dialogContainer;
}

-(CGSize)getScreenSize{
    CGFloat screenWidth = [UIScreen mainScreen].bounds.size.width;
    CGFloat screenHeight = [UIScreen mainScreen].bounds.size.height;
    
    UIInterfaceOrientation interfaceOrientation = [[UIApplication sharedApplication] statusBarOrientation];
    if (UIInterfaceOrientationIsLandscape(interfaceOrientation)) {
        CGFloat tmp = screenWidth;
        screenWidth = screenHeight;
        screenHeight = tmp;
    }
    
    return CGSizeMake(screenWidth, screenHeight);
}

-(CGSize)getPopViewSize{
    
    CGFloat dialogWidth = _pickerTableView.frame.size.width;
    CGFloat dialogHeight = _pickerTableView.frame.size.height + 40 + 0;
    
    return CGSizeMake(dialogWidth, dialogHeight);
}

- (void)addButtonsToView: (UIView *)container
{
    
    UIButton *closeButton = [UIButton buttonWithType:UIButtonTypeCustom];
    closeButton.frame = CGRectMake(0, container.bounds.size.height - _buttonHeight, container.bounds.size.width, _buttonHeight);
    
    [closeButton setTitle:@"OK" forState:UIControlStateNormal];
    [closeButton setTitleColor:[UIColor colorWithRed:0.0f green:0.5f blue:1.0f alpha:1.0f] forState:UIControlStateNormal];
    [closeButton setTitleColor:[UIColor colorWithRed:0.2f green:0.2f blue:0.2f alpha:0.5f] forState:UIControlStateHighlighted];
    [closeButton.titleLabel setFont:[UIFont boldSystemFontOfSize:16.0f]];
    [closeButton.layer setCornerRadius:kPopViewCornerRadius];
    [closeButton addTarget:self action:@selector(dismiss) forControlEvents:UIControlEventTouchUpInside];
    [container addSubview:closeButton];
    
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    NSString *identifier = @"cell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
    }
//    NSAssert(self.dataSource != nil, @"popView's dataSource shouldn't be nil");
    if ([self.dataSource respondsToSelector:@selector(popPickerTitleAtIndex:)]) {
        cell.textLabel.text = [self.dataSource popPickerTitleAtIndex:indexPath.row];
    }else{
        NSAssert(0, @"required method of dataSource protocol should be implemented");
    }
    if (_indexPath && _indexPath.row == indexPath.row) {
        cell.accessoryType = UITableViewCellAccessoryCheckmark;
    }else{
        cell.accessoryType = UITableViewCellAccessoryNone;
    }
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
//    NSAssert(self.dataSource != nil, @"popView's dataSource shouldn't be nil");
    if ([self.dataSource respondsToSelector:@selector(popPickerItemNumber)]) {
        return [self.dataSource popPickerItemNumber];
    }else{
        NSAssert(0, @"required method of dataSource protocol should be implemented");
    }
    return 0;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    if (_indexPath) {
        UITableViewCell *oneCell = [tableView cellForRowAtIndexPath:_indexPath];
        [tableView deselectRowAtIndexPath:_indexPath animated:NO];
        oneCell.accessoryType = UITableViewCellAccessoryNone;
    }
    _indexPath = indexPath;
     UITableViewCell *oneCell = [tableView cellForRowAtIndexPath: indexPath];
    oneCell.accessoryType = UITableViewCellAccessoryCheckmark;
    
    if ([self.delegate respondsToSelector:@selector(popPickerDidSelectedIndex:)]) {
        [self.delegate popPickerDidSelectedIndex:indexPath.row];
    }
    
    self.titleLabel.text = [self.dataSource popPickerTitleAtIndex:indexPath.row];
    
}

-(void)setSelected:(NSInteger)selected{
    _indexPath = [NSIndexPath indexPathForRow:selected inSection:0];
    _selected = selected;
}

- (CAShapeLayer *)createIndicatorWithColor:(UIColor *)color andPosition:(CGPoint)point {
    CAShapeLayer *layer = [CAShapeLayer new];
    
    UIBezierPath *path = [UIBezierPath new];
    [path moveToPoint:CGPointMake(0, 0)];
    [path addLineToPoint:CGPointMake(20, 0)];
    [path addLineToPoint:CGPointMake(10, 15)];
    [path closePath];
    
    layer.path = path.CGPath;
    layer.lineWidth = 1.0;
    layer.fillColor = color.CGColor;
    
    CGPathRef bound = CGPathCreateCopyByStrokingPath(layer.path, nil, layer.lineWidth, kCGLineCapButt, kCGLineJoinMiter, layer.miterLimit);
    layer.bounds = CGPathGetBoundingBox(bound);
    
    layer.position = point;
    
    return layer;
}

// Handle device orientation changes
- (void)deviceOrientationDidChange: (NSNotification *)notification
{
    // If dialog is attached to the parent view, it probably wants to handle the orientation change itself
    
    UIInterfaceOrientation interfaceOrientation = [[UIApplication sharedApplication] statusBarOrientation];
    
    CGFloat startRotation = [[self valueForKeyPath:@"layer.transform.rotation.z"] floatValue];
    CGAffineTransform rotation;
    
    switch (interfaceOrientation) {
        case UIInterfaceOrientationLandscapeLeft:
            rotation = CGAffineTransformMakeRotation(-startRotation + M_PI * 270.0 / 180.0);
            break;
            
        case UIInterfaceOrientationLandscapeRight:
            rotation = CGAffineTransformMakeRotation(-startRotation + M_PI * 90.0 / 180.0);
            break;
            
        case UIInterfaceOrientationPortraitUpsideDown:
            rotation = CGAffineTransformMakeRotation(-startRotation + M_PI * 180.0 / 180.0);
            break;
            
        default:
            rotation = CGAffineTransformMakeRotation(-startRotation + 0.0);
            break;
    }
    
//    [UIView animateWithDuration:0.2f delay:0.0 options:UIViewAnimationOptionTransitionNone
//                     animations:^{
//                         dialogView.transform = rotation;
//                     }
//                     completion:^(BOOL finished){
//                         // fix errors caused by being rotated one too many times
//                         dispatch_after(dispatch_time(DISPATCH_TIME_NOW, 0.5f * NSEC_PER_SEC), dispatch_get_main_queue(), ^{
//                             UIInterfaceOrientation endInterfaceOrientation = [[UIApplication sharedApplication] statusBarOrientation];
//                             if (interfaceOrientation != endInterfaceOrientation) {
//                                 // TODO user moved phone again before than animation ended: rotation animation can introduce errors here
//                             }
//                         });
//                     }
//     ];
    
}


-(void)dealloc{
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIDeviceOrientationDidChangeNotification object:nil];
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
    
}
 */

@end

//
//  FECloudControlVC.m
//  SmartHome
//
//  Created by Seven on 14-10-20.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECloudControlVC.h"
#import <RATreeView/RATreeView.h>
#import "FEControlObject.h"
#import "FEControlPointCell.h"
#import "FETreeViewCell.h"

@interface FECloudControlVC ()<RATreeViewDataSource,RATreeViewDelegate>

@property (nonatomic, strong) RATreeView *controlTree;
@property (strong, nonatomic) NSArray *data;

@end

@implementation FECloudControlVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"CLOUD_CONTROL");
        [self loadData];
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
}

-(void)initUI{
    RATreeView *treeView = [[RATreeView alloc] initWithFrame:self.view.bounds];
    treeView.delegate = self;
    treeView.dataSource = self;
    treeView.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    [self.view addSubview:treeView];
    [treeView reloadData];
    self.controlTree = treeView;
    
    UIView *view = [UIView new];
    view.backgroundColor = [UIColor clearColor];
}

#pragma mark TreeView Delegate methods

- (CGFloat)treeView:(RATreeView *)treeView heightForRowForItem:(id)item
{
    return 44;
}

- (BOOL)treeView:(RATreeView *)treeView canEditRowForItem:(id)item
{
    return YES;
}

- (void)treeView:(RATreeView *)treeView willExpandRowForItem:(id)item
{
//    UITableViewCell *cell = (UITableViewCell *)[treeView cellForItem:item];
//    [cell setAdditionButtonHidden:NO animated:YES];
}

- (void)treeView:(RATreeView *)treeView willCollapseRowForItem:(id)item
{
//    RATableViewCell *cell = (RATableViewCell *)[treeView cellForItem:item];
//    [cell setAdditionButtonHidden:YES animated:YES];
}


#pragma mark TreeView Data Source

- (UITableViewCell *)treeView:(RATreeView *)treeView cellForItem:(id)item
{
    NSInteger level = [treeView levelForCellForItem:item];
    
    FETreeViewCell *cell = [treeView dequeueReusableCellWithIdentifier:NSStringFromClass([FETreeViewCell class])];
    if (!cell) {
        cell = [[FETreeViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:NSStringFromClass([FETreeViewCell class])];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
    }
    [cell configurelevel:level withTitle:((FEControlObject *)item).name];
    return cell;
    
    return cell;
}

- (NSInteger)treeView:(RATreeView *)treeView numberOfChildrenOfItem:(id)item
{
    if (item == nil) {
        return [self.data count];
    }
    
    FEControlObject *data = item;
    return [data.children count];
}

- (id)treeView:(RATreeView *)treeView child:(NSInteger)index ofItem:(id)item
{
    FEControlObject *data = item;
    if (item == nil) {
        return [self.data objectAtIndex:index];
    }
    
    return data.children[index];
}

- (void)loadData
{
    FEControlObject *control1 = [FEControlObject dataObjectWithName:@"门控制器1:厨房" children:nil];
    FEControlObject *control2 = [FEControlObject dataObjectWithName:@"门控制器2:卧室" children:nil];
    
    FEControlObject *doorcontrol = [FEControlObject dataObjectWithName:@"门控制器"
                                                  children:[NSArray arrayWithObjects:control1, control2, nil]];
    
    FEControlObject *notebook1 = [FEControlObject dataObjectWithName:@"窗帘控制器1" children:nil];
    FEControlObject *notebook2 = [FEControlObject dataObjectWithName:@"窗帘控制器2" children:nil];
    
    FEControlObject *computer1 = [FEControlObject dataObjectWithName:@"窗帘控制器"
                                                      children:[NSArray arrayWithObjects:notebook1, notebook2, nil]];
    FEControlObject *computer2 = [FEControlObject dataObjectWithName:@"灭火控制器1" children:nil];
    FEControlObject *computer3 = [FEControlObject dataObjectWithName:@"灭火控制器2" children:nil];
    
    FEControlObject *computer = [FEControlObject dataObjectWithName:@"灭火控制器"
                                                     children:[NSArray arrayWithObjects:computer2, computer3, nil]];
    
    self.data = [NSArray arrayWithObjects:doorcontrol, computer1,computer, nil];
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end

use strict;
use warnings;
use File::Find;

my $total_size = 0;

sub wanted {
    if (-f $_) {  # Only process files
        $total_size += -s _;
    }
}

find(\&wanted, '.');  # Start at current directory

print "Total size: $total_size bytes\n";
